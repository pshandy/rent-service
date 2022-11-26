package com.pshandy.rentservice.service;

import com.pshandy.rentservice.persistence.model.User;
import com.pshandy.rentservice.web.dto.UserDto;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto);

}
