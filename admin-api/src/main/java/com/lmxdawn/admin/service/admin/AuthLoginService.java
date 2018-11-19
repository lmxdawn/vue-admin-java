package com.lmxdawn.admin.service.admin;

import com.lmxdawn.admin.dto.admin.LoginUserInfoDTO;
import com.lmxdawn.admin.form.admin.LoginForm;
import com.lmxdawn.admin.form.admin.UpdatePasswordForm;

import java.util.Map;

public interface AuthLoginService {


    Map<String, Object> loginToken(LoginForm loginForm);

    LoginUserInfoDTO findByAdminId(Long adminId);

    boolean updatePassword(UpdatePasswordForm updatePasswordForm);

}
