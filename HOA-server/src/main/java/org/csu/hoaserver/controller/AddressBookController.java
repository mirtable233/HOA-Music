package org.csu.hoaserver.controller;


import DO.AddressBook;
import context.UserContext;
import org.csu.hoaserver.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import response.CommonResponse;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
//@Api(tags = "C端地址簿接口")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 查询当前登录用户的所有地址信息
     *
     * @return
     */
    @GetMapping("/list")
    public CommonResponse<List<AddressBook>> list() {
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(UserContext.getCurrentId());
        List<AddressBook> list = addressBookService.list(addressBook);
        return CommonResponse.createForSuccess(list);
    }

    /**
     * 新增地址
     *
     * @param addressBook
     * @return
     */
    @PostMapping
    public CommonResponse<String> save(@RequestBody AddressBook addressBook) {
        addressBookService.save(addressBook);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/{id}")
    public CommonResponse<AddressBook> getById(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        return CommonResponse.createForSuccess(addressBook);
    }

    /**
     * 根据id修改地址
     *
     * @param addressBook
     * @return
     */
    @PutMapping
    public CommonResponse<String> update(@RequestBody AddressBook addressBook) {
        addressBookService.update(addressBook);
        return CommonResponse.createForSuccess();
    }

    /**
     * 设置默认地址
     *
     * @param addressBook
     * @return
     */
    @PutMapping("/default")
    public CommonResponse<String> setDefault(@RequestBody AddressBook addressBook) {
        addressBookService.setDefault(addressBook);
        return CommonResponse.createForSuccess();
    }

    /**
     * 根据id删除地址
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public CommonResponse<String> deleteById(Long id) {
        addressBookService.deleteById(id);
        return CommonResponse.createForSuccess();
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public CommonResponse<AddressBook> getDefault() {
        //SQL:select * from address_book where user_id = ? and is_default = 1
        AddressBook addressBook = new AddressBook();
        addressBook.setIsDefault(1);
        addressBook.setUserId(UserContext.getCurrentId());
        List<AddressBook> list = addressBookService.list(addressBook);

        if (list != null && list.size() == 1) {
            return CommonResponse.createForSuccess(list.get(0));
        }

        return CommonResponse.createForError("没有查询到默认地址");
    }

}
