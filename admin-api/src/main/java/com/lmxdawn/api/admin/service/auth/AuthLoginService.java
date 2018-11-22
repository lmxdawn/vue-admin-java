package com.lmxdawn.api.admin.service.auth;

import com.lmxdawn.api.admin.dto.auth.LoginUserInfoDTO;
import com.lmxdawn.api.admin.form.admin.auth.LoginForm;
import com.lmxdawn.api.admin.form.admin.auth.UpdatePasswordForm;

import java.util.Map;

public interface AuthLoginService {


    Map<String, Object> loginToken(LoginForm loginForm);

    LoginUserInfoDTO findByAdminId(Long adminId);

    boolean updatePassword(UpdatePasswordForm updatePasswordForm);

}
