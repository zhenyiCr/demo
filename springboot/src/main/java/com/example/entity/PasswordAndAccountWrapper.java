package com.example.entity;

import lombok.Data;

@Data
public class PasswordAndAccountWrapper {
    private ChangePasswordDTO changePasswordDTO;
    private Account account;
}
