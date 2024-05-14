package com.bu200.project.service;

import com.bu200.common.response.Tool;
import com.bu200.login.entity.Account;
import com.bu200.project.entity.Project;
import com.bu200.project.entity.ProjectMember;
import com.bu200.project.repository.ProjectMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ModelMapper modelMapper;
    private final Tool tool;
    private Pageable pageable;

    public ProjectService(ProjectRepository projectRepository, ProjectMemberRepository projectMemberRepository, ModelMapper modelMapper, Tool tool) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.modelMapper = modelMapper;
        this.tool = tool;
    }

    public Page<Project> findByAccountCode(Long userCode, int page) {
        pageable = PageRequest.of(page,6);
        Account account = new Account();
        account.setAccountCode(userCode);
        List<ProjectMember> member = projectMemberRepository.findByAccountCode(account);
        List<Long> memberCode = new ArrayList<>();
        for (ProjectMember projectMember : member) {
            memberCode.add(projectMember.getProjectCode());
        }
        Page<Project> projects = projectRepository.findAllByProjectCodeIn(memberCode,pageable);
        for(Project pr : projects){
            System.out.println(pr.getProjectName());
        }
        return projects;
    }

    public Page<Project> findAllByProjectOpenStatusTrue(int page) {
        pageable = PageRequest.of(page,6);
        return projectRepository.findAllByProjectOpenStatusTrue(pageable);
    }

    public Project findById(Long projectCode) {
        return projectRepository.findById(projectCode).orElseThrow();
    }
}
