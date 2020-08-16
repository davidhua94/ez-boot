package com.ezboot.system.admin.dto;

import com.ezboot.core.base.dto.AbstractDTO;
import lombok.Data;

/**
 * @author david
 * Reset Password Params
 */
@Data
public class ResetPasswordDTO extends AbstractDTO {
    private String oldPassword;
    private String oldPasswordConfirm;
    private String newPassword;
}
