create table if not exists employee
(
    employee_id   bigint      not null auto_increment primary key comment '회원 ID',
    first_name    varchar(20) not null comment '회원 FIRST NAME',
    last_name     varchar(20) not null comment '회원 LAST NAME',
    department_id bigint      null comment '부서 ID',
    index idx_department_id (department_id)
);

create table if not exists department
(
    department_id bigint      not null auto_increment primary key comment '부서 ID',
    dept_name     varchar(50) not null comment '부서명',
    team_lead_id  bigint      null comment '부서장 ID',
    constraint fk_department_team_lead_id foreign key (team_lead_id) references employee (employee_id) on delete set null on update cascade,
    index idx_team_lead_id (team_lead_id)
);

alter table employee
    add constraint fk_employee_department_id foreign key (department_id) references department (department_id) on delete set null on update cascade;