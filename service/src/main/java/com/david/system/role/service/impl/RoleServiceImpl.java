package com.david.system.role.service.impl;

import com.david.core.base.MessageCode;
import com.david.core.base.PageResult;
import com.david.core.base.service.impl.BaseServiceImpl;
import com.david.core.exception.ServiceException;
import com.david.system.role.dto.RoleDTO;
import com.david.system.role.dto.RoleListQueryDTO;
import com.david.system.role.entity.Role;
import com.david.system.role.repository.RoleRepository;
import com.david.system.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wang
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        save(role);
    }

    @Override
    public void update(RoleDTO roleDTO) {
        Optional<Role> existRole = roleRepository.findById(roleDTO.getId());
        Role role = existRole.orElseThrow(() -> new ServiceException(MessageCode.NOT_FOUND));
        BeanUtils.copyProperties(roleDTO, role);
        update(role);
    }

    @Override
    public PageResult<Role> pageList(RoleListQueryDTO pageQuery) {
        Pageable pageable = PageRequest.of(pageQuery.getPageIndex() - 1, pageQuery.getPageSize());
        Page<Role> rolePage = roleRepository.findAll((Specification<Role>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            // TODO 查询条件
            if (StringUtils.isNotBlank(pageQuery.getName())) {
                Predicate roleNamePredicate = criteriaBuilder.like(root.get("roleName").as(String.class),
                        "%" + pageQuery.getName() + "%");
                list.add(roleNamePredicate);
            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        },pageable);

        /**
         * TODO 转化成DTO返回, 实际不能将entity返回给web层
         */
        PageResult<Role> result = PageResult.<Role>builder().currentPage(pageQuery.getPageIndex())
                        .pageSize(pageQuery.getPageSize())
                        .totalCount((int)rolePage.getTotalElements())
                        .totalPage(rolePage.getTotalPages())
                        .data(rolePage.getContent())
                        .build();
        return result;
    }
}