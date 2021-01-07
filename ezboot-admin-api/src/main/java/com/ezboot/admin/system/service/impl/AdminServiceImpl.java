package com.ezboot.admin.system.service.impl;

import com.ezboot.admin.system.constant.AdminCode;
import com.ezboot.admin.system.dto.AdminListDTO;
import com.ezboot.admin.system.dto.AdminListQueryDTO;
import com.ezboot.admin.system.dto.AdminLoginDTO;
import com.ezboot.admin.system.dto.ResetPasswordDTO;
import com.ezboot.admin.system.entity.Admin;
import com.ezboot.admin.exception.LoginException;
import com.ezboot.admin.system.repository.AdminRepository;
import com.ezboot.admin.exception.AdminServiceException;
import com.ezboot.admin.system.repository.RoleRepository;
import com.ezboot.admin.system.service.AdminService;
import com.ezboot.admin.system.service.PermissionService;
import com.ezboot.admin.system.service.RoleService;
import com.ezboot.core.CurrentAdmin;
import com.ezboot.core.WebContext;
import com.ezboot.core.base.PageResult;
import com.ezboot.core.base.service.impl.BaseServiceImpl;
import com.ezboot.core.constant.GlobalConstants;
import com.ezboot.core.util.GsonUtil;
import com.ezboot.core.util.JedisUtil;
import com.ezboot.core.util.MD5Helper;
import com.ezboot.core.util.UUIDGenerator;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @author wang
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    private static final String ADMIN_PASSWORD_PREFIX = "EZ-Boot";

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String login(AdminLoginDTO loginRequest) {
        Admin admin = adminRepository.findByUsername(loginRequest.getUsername());
        if (admin == null) {
            throw new LoginException(AdminCode.USERNAME_NOT_EXIST);
        }

        if (!admin.isEnabled()) {
            throw new LoginException(AdminCode.USERNAME_LOCKED);
        }

        String encryptPwd = encryptPwd(loginRequest.getPassword());
        if (!encryptPwd.equals(admin.getPassword())) {
            throw new LoginException(AdminCode.USERNAME_PASSWORD_UN_MATCH);
        }

        // 更新上次登陆时间、登陆ip
        adminRepository.updateLoginStatus(admin.getId(), WebContext.getRequestIp(), Calendar.getInstance().getTime());


        CurrentAdmin currentUser = new CurrentAdmin();
        BeanUtils.copyProperties(admin, currentUser);
        currentUser.setPermissions(Sets.newHashSet("*"));
        // 数据库有数据之后放开
//        currentUser.setPermissions(this.getPermissions(admin.getId()));

        String token = UUIDGenerator.randomUUID();
        // 默认30分钟
        JedisUtil.put(token, GsonUtil.obj2Str(currentUser));
        JedisUtil.setExpire(token, GlobalConstants.ADMIN_TOKEN_EXPIRE);

        return token;
    }

    /**
     * 从数据库获得此管理员所有权限
     */
    @Override
    public Set<String> getPermissions(Integer adminId) {
        List<Integer> roleIds = roleService.getRoleIdsByAdminId(adminId);
        if (roleIds == null || roleIds.isEmpty()) {
            return Sets.newHashSet();
        }

        return permissionService.getPermissions(roleIds);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if (!resetPasswordDTO.getOldPassword().equals(resetPasswordDTO.getOldPasswordConfirm())) {
            throw new AdminServiceException(AdminCode.TWO_PASSWORD_UN_MATCH);
        }

        if (resetPasswordDTO.getOldPassword().equals(resetPasswordDTO.getNewPassword())) {
            throw new AdminServiceException(AdminCode.OLD_NEW_PASSWORD_SAME);
        }

        // TODO

    }

    @Override
    public PageResult<AdminListDTO> pageList(AdminListQueryDTO queryDTO) {
        Pageable pageable = buildPageRequest(queryDTO);
        Page<Admin> adminPage = adminRepository.findAll((Specification<Admin>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(queryDTO.getUsername())) {
                Predicate usernamePredicate = criteriaBuilder.like(root.get("username").as(String.class),
                        "%" + queryDTO.getUsername() + "%");
                list.add(usernamePredicate);
            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        },pageable);

        List<AdminListDTO> dataList = convertToDTO(adminPage.getContent());

        return PageResult.<AdminListDTO>builder().data(dataList).totalCount((int)adminPage.getTotalElements()).build();
    }

    private List<AdminListDTO> convertToDTO(List<Admin> content) {
        List<AdminListDTO> result = Lists.newArrayList();
        content.forEach(entity -> {
            AdminListDTO dto = new AdminListDTO();
            BeanUtils.copyProperties(entity, dto);
            dto.setRoleList(roleRepository.findRoleNameListByAdminId(entity.getId()));
            result.add(dto);
        });
        return result;
    }

    /**
     * @param password 加密前的密码
     * @return 加密后的密码
     */
    private String encryptPwd(String password) {
        String formatPwd = String.format("%s%s", ADMIN_PASSWORD_PREFIX, password);
        return MD5Helper.md5(formatPwd);
    }

    public static void main(String[] args) {
        String pwd = "123456";
        String formatPwd = String.format("%s%s", ADMIN_PASSWORD_PREFIX, pwd);
        System.out.println(MD5Helper.md5(formatPwd));
    }

}
