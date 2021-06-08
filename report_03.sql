-- 문제1 질의문
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

-- 문제2.
-- dept_section_cnt 뷰 정의
create view dept_section_cnt as
select dept_name, year, count(*) as cnt_courses
from section, course
where section.course_id = course.course_id
group by dept_name, year

-- dept_section_cnt 뷰 삭제
drop view dept_section_cnt

-- dept_section_cnt 뷰 확인
select *
from dept_section_cnt

select *
from advisor

select *
from instructor

-- 문제3.
-- inst_advise_student 함수 정의
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

-- 교수 정보 및 교수별 지도학생수 확인
select ID, name, count(s_ID) as student_cnt
from advisor, instructor
where i_ID = ID
group by ID, name
order by name asc

-- 위 질의문과 같은 동작을 하는지 확인
select *, dbo.inst_advise_student(name) as student_cnt
from instructor
order by name asc
