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

create table if not exists role
(
    role_id bigint      not null auto_increment primary key comment '역할 ID',
    name    varchar(45) not null comment '역할명'
);

create table if not exists employee_role_mapping
(
    id          bigint not null auto_increment primary key comment 'ID',
    employee_id bigint not null comment '회원 ID',
    role_id     bigint not null comment '역할 ID',
    constraint fk_employee_role_mapping_employee_id foreign key (employee_id) references employee (employee_id) on delete cascade on update cascade,
    constraint fk_employee_role_mapping_role_id foreign key (role_id) references role (role_id) on delete cascade on update cascade,
    index idx_employee_id (employee_id),
    index idx_role_id (role_id)
);