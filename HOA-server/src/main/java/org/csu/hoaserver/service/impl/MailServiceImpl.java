package org.csu.hoaserver.service.impl;

import DO.User;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.time.Duration;

@Service("MailService")
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements org.csu.hoaserver.service.MailService {
    private final JavaMailSender mailSender;
    private final StringRedisTemplate redis;
    @Value("${spring.mail.username}")
    private String fromAddress;
    @Value("${mail.from.name}")
    private String fromName;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final String KEY_RESET_PREFIX = "verify:reset:";

    public void verify(User user) throws MailException {
        String role = user.getRole().getDisplayName();
        String toAddress = user.getEmail();
        final String code = String.format("%06d", SECURE_RANDOM.nextInt(1_000_000));
        String redisKey = "verify:email:" + toAddress;
        redis.opsForValue().set(redisKey, code, Duration.ofMinutes(2));
        MimeMessagePreparator preparatory = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            mimeMessage.setFrom(new InternetAddress(fromAddress, fromName));
            mimeMessage.setSubject("HOA音乐注册验证码");
            String htmlContent = buildHtmlContent(user, role,code);
            mimeMessage.setContent(htmlContent, "text/html;charset=UTF-8");
        };
        mailSender.send(preparatory);
        log.info("验证码 {} 已发送至 {}", code, toAddress);
    }

    public void verifyReset(User user) {
        // 0. 邮箱判空
        String toAddress = user.getEmail();
        if (!StringUtils.hasText(toAddress)) {
            throw new MailPreparationException("用户邮箱为空");
        }

        // 1. 生成 6 位验证码
        String code = String.format("%06d", SECURE_RANDOM.nextInt(1_000_000));

        // 2. Redis 保存 10 分钟
        redis.opsForValue().set("verify:reset:" + toAddress, code, Duration.ofMinutes(2));

        // 3. 发送邮件
        mailSender.send(mime -> {
            MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
            helper.setTo(toAddress);
            helper.setFrom(fromAddress, fromName);        // 自动处理编码
            helper.setSubject("HOA音乐重设密码验证码");
            helper.setText(buildHtmlContentReset(user, code), true);
        });

        log.info("重设密码验证码 {} 已发送至 {}", code, toAddress);
    }


    public String buildHtmlContent(User user, String role, String verificationCode) {
        return "<html>" +
                "<head>" +
                "<style>" +
                "  @import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@700&display=swap');" +
                "  body {" +
                "    margin:0; padding:40px; background: linear-gradient(135deg, #0a1128, #000000);" +  // 深蓝到黑色渐变
                "    font-family: 'Orbitron', 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" +
                "    color: #f0f0f5;" +
                "  }" +

                "  .container {" +
                "    max-width: 600px; margin: auto; background: radial-gradient(circle at top left, #112240, #000000);" + // 深蓝渐变
                "    border-radius: 20px; padding: 40px 50px;" +
                "    box-shadow: 0 0 35px 8px #ff6f61aa, inset 0 0 30px 5px #ff572288;" +  // 橙红色阴影
                "    border: 2px solid #ff6f61;" +
                "  }" +

                "  h1 {" +
                "    font-size: 3rem; text-align: center; text-transform: uppercase;" +
                "    letter-spacing: 6px; color: #ff6f61;" + // 橙红主色
                "    text-shadow: 0 0 15px #ff6f61, 0 0 35px #ff4123;" +
                "    margin-bottom: 35px;" +
                "  }" +

                "  p {" +
                "    font-size: 1.1rem; line-height: 1.6;" +
                "    color: #d1d7e0;" +
                "    text-shadow: 0 0 5px #1b2735cc;" +  // 深蓝阴影文字更柔和
                "  }" +

                "  p strong {" +
                "    color: #ff6f61;" +
                "    text-shadow: 0 0 8px #ff6f61bb;" +
                "  }" +

                "  .verification-code {" +
                "    display: inline-block;" +
                "    font-size: 38px; font-weight: 900; letter-spacing: 14px;" +
                "    padding: 22px 60px;" +
                "    margin: 45px auto;" +
                "    border: 5px double #ff6f61;" +
                "    border-radius: 20px;" +
                "    background: linear-gradient(45deg, #ff6f61cc, #66332dcc);" +
                "    box-shadow:" +
                "      0 0 20px #ff6f61," +
                "      0 0 40px #ff947a," +
                "      0 0 60px #ffad95;" +
                "    text-align: center;" +
                "    animation: pulse 2.5s infinite alternate;" +
                "    user-select: all;" +
                "    font-family: 'Courier New', Courier, monospace;" +
                "  }" +

                "  @keyframes pulse {" +
                "    0%, 100% {opacity: 1; box-shadow: 0 0 20px #ff6f61, 0 0 40px #ff947a, 0 0 70px #ffad95;}" +
                "    50% {opacity: 0.8; box-shadow: 0 0 8px #cc554c, 0 0 20px #ff6f61, 0 0 35px #ff947a;}" +
                "  }" +

                "  hr {" +
                "    border: none; height: 1px;" +
                "    background: linear-gradient(to right, transparent, #ff6f61, transparent);" +
                "    margin: 40px 0;" +
                "  }" +

                "  .footer {" +
                "    margin-top: 50px; font-size: 13px; color: #552a23;" +
                "    text-align: center; font-style: italic;" +
                "    text-shadow: 0 0 8px #331911;" +
                "  }" +

                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +

                "<h1>欢迎加入 HOA 音乐</h1>" +

                "<p>尊敬的 <strong>" + user.getUsername() + " " + role + "</strong>，您好：</p>" +
                "<p>感谢您注册 HOA 音乐平台。以下是您的 <strong>注册验证码</strong>，有效期 <strong>2 分钟</strong>：</p>" +

                "<div class='verification-code'>" + verificationCode + "</div>" +

                "<p>请在验证页面输入此验证码以完成注册。如果您未发起此请求，请忽略此邮件。</p>" +

                "<hr />" +

                "<div class='footer'>" +
                "此邮件由系统自动发送，请勿直接回复。<br/>" +
                "—— HOA 音乐团队" +
                "</div>" +

                "</div>" +
                "</body>" +
                "</html>";
    }


    public String buildHtmlContentReset(User user, String verificationCode) {
        return "<html>" +
                "<head>" +
                "<style>" +
                "  @import url('https://fonts.googleapis.com/css2?family=Orbitron:wght@700&display=swap');" +
                "  body {" +
                "    margin:0; padding:40px; background: linear-gradient(135deg, #0a1128, #000000);" +
                "    font-family: 'Orbitron', 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" +
                "    color: #f0f0f5;" +
                "  }" +

                "  .container {" +
                "    max-width: 600px; margin: auto; background: radial-gradient(circle at top left, #112240, #000000);" +
                "    border-radius: 20px; padding: 40px 50px;" +
                "    box-shadow: 0 0 35px 8px #ff6f61aa, inset 0 0 30px 5px #ff572288;" +
                "    border: 2px solid #ff6f61;" +
                "  }" +

                "  h2 {" +
                "    font-size: 2.8rem; text-align: center; text-transform: uppercase;" +
                "    letter-spacing: 6px; color: #ff6f61;" +
                "    text-shadow: 0 0 15px #ff6f61, 0 0 35px #ff4123;" +
                "    margin-bottom: 35px;" +
                "  }" +

                "  p {" +
                "    font-size: 1.1rem; line-height: 1.6;" +
                "    color: #d1d7e0;" +
                "    text-shadow: 0 0 5px #1b2735cc;" +
                "  }" +

                "  p strong {" +
                "    color: #ff6f61;" +
                "    text-shadow: 0 0 8px #ff6f61bb;" +
                "  }" +

                "  .verification-code {" +
                "    display: inline-block;" +
                "    font-size: 36px; font-weight: 900; letter-spacing: 14px;" +
                "    padding: 20px 55px;" +
                "    margin: 30px auto;" +
                "    border: 5px double #ff6f61;" +
                "    border-radius: 20px;" +
                "    background: linear-gradient(45deg, #ff6f61cc, #66332dcc);" +
                "    box-shadow:" +
                "      0 0 20px #ff6f61," +
                "      0 0 40px #ff947a," +
                "      0 0 60px #ffad95;" +
                "    text-align: center;" +
                "    animation: pulse 2.5s infinite alternate;" +
                "    user-select: all;" +
                "    font-family: 'Courier New', Courier, monospace;" +
                "  }" +

                "  @keyframes pulse {" +
                "    0%, 100% {opacity: 1; box-shadow: 0 0 20px #ff6f61, 0 0 40px #ff947a, 0 0 70px #ffad95;}" +
                "    50% {opacity: 0.8; box-shadow: 0 0 8px #cc554c, 0 0 20px #ff6f61, 0 0 35px #ff947a;}" +
                "  }" +

                "  hr {" +
                "    border: none; height: 1px;" +
                "    background: linear-gradient(to right, transparent, #ff6f61, transparent);" +
                "    margin: 40px 0;" +
                "  }" +

                "  .footer {" +
                "    margin-top: 50px; font-size: 13px; color: #552a23;" +
                "    text-align: center; font-style: italic;" +
                "    text-shadow: 0 0 8px #331911;" +
                "  }" +

                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +

                "<h2>重设密码通知</h2>" +

                "<p>尊敬的 <strong>" + user.getUsername() + " 用户</strong>，您好：</p>" +

                "<p>我们收到您在 <strong>HOA 音乐</strong> 平台的 <strong>密码重设请求</strong>。</p>" +

                "<p>以下是您的重设验证码（有效期 <strong>10 分钟</strong>）：</p>" +

                "<div class='verification-code'>" + verificationCode + "</div>" +

                "<p>如果您并未发起此操作，请忽略此邮件；您的密码将保持不变。</p>" +

                "<hr />" +

                "<div class='footer'>" +
                "此邮件由系统自动发送，请勿直接回复。<br/>" +
                "—— HOA 音乐团队" +
                "</div>" +

                "</div>" +
                "</body>" +
                "</html>";
    }


    public String validate(String email, String inputCode) {
            String key = "verify:email:" + email;
            String real = redis.opsForValue().get(key);
            log.info("从Redis取验证码，key = {}, value = {}", key, real);
            if (real == null) {
                return "null";
            }
            if (!real.equals(inputCode)) {
                return "fail";
            }
            redis.delete(key);
            return "success";// 一次性使用
        }
    public String validateReset(String email, String inputCode) {
        // 与 verifyReset 写入时保持一致的前缀
        String key = "verify:reset:" + email;
        String real = redis.opsForValue().get(key);
        log.info("从 Redis 取重设验证码，key = {}, value = {}", key, real);

        if (real == null) {
            return "null";      // 未找到或已过期
        }
        if (!real.equals(inputCode)) {
            return "fail";      // 不匹配
        }
        redis.delete(key);      // 一次性使用，确保安全
        return "success";
    }

}
