-- 문제1. 학과명이 'Physics'인 학과에 소속된 학생들의 이름과 학생 별 총 이수학점 검색
select name, tot_cred
from student
where dept_name = 'Physics'


-- 문제2. 학과명이 'Physics'인 학과가 운영하는 교과목 중 이수학점이 3학점 이상인 교과목들의 교과목명과 이수학점 검색
select title, credits
from course
where (dept_name = 'Physics') and (credits >= 3)

-- 문제 3. 예산이 100000 이상인 학과에 소속된 교수들의 이름과 교수별 연봉 검색
-- department.dept_name = instructor.dept_name => 자연 조인 연산
select name, salary, instructor.dept_name
from instructor, department
where (department.budget >= 100000) and (department.dept_name = instructor.dept_name)

-- course 릴레이션
select * from course

-- instructor 릴레이션
select* from instructor

-- teaches 릴레이션
select * from teaches where (semester = 'Fall') and (year = 2009)

-- 문제4. 2009학년도 가을(Fall) 학기에 강의를 담당하였던 교수들의 이름과 소속학과 검색
-- instructor.ID = teaches.ID => 자연 조인 연산
select instructor.name as instructor_name, instructor.dept_name, semester, year
from instructor, teaches
where (semester = 'Fall') and (year = 2009) and (instructor.ID = teaches.ID)

-- advisor 릴레이션
select * from advisor

-- 문제5. Physics 학과에 소속된 교수들로부터 지도받는 학생들의 이름 검색
-- (i_ID = instructor.ID) and (s_ID = student.ID) => 자연 조인 연산
select student.name as student_name, instructor.name as instructor_name, student.dept_name as s_dept_name, instructor.dept_name as i_dept_name
from advisor, instructor, student
where (i_ID = instructor.ID) and (s_ID = student.ID) and (instructor.dept_name = 'Physics')

-- 물리학과 지도 교수
select name from instructor where dept_name = 'Physics'
