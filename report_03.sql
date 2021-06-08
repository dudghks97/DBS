-- ����1 ���ǹ�
select distinct ID, title
from teaches, course
where teaches.course_id = course.course_id

select distinct *
from teaches, course
where teaches.course_id = course.course_id and ID = 22591

select distinct title
from teaches, course
where teaches.course_id = course.course_id and ID = 16453

select title
from teaches, course
where teaches.course_id = course.course_id and ID = 22591

-- ����2.
-- dept_section_cnt �� ����
create view dept_section_cnt as
select dept_name, year, count(*) as cnt_courses
from section, course
where section.course_id = course.course_id
group by dept_name, year

-- dept_section_cnt �� ����
drop view dept_section_cnt

-- dept_section_cnt �� Ȯ��
select *
from dept_section_cnt

select *
from advisor

select *
from instructor

-- ����3.
-- inst_advise_student �Լ� ����
create function inst_advise_student(@inst_name varchar(20))
returns integer
as
begin
declare @s_count integer
select @s_count=count(*)
from advisor as A, instructor as I
where A.i_ID = I.ID and I.name = @inst_name
return @s_count
end

select name
from instructor

-- ���� ���� �� ������ �����л��� Ȯ��
select ID, name, count(s_ID) as student_cnt
from advisor, instructor
where i_ID = ID
group by ID, name
order by name asc

-- �� ���ǹ��� ���� ������ �ϴ��� Ȯ��
select *, dbo.inst_advise_student(name) as student_cnt
from instructor
order by name asc
