-- 검색 요구 6번에 대한 처리를 위한 SQL문.
-- 학과명이 'Pol. Sci.' 이거나 'Comp. Sci.' 인 교수들의 연봉을 null 값으로 바꿈.
update instructor
set salary = null
where dept_name = 'Pol. Sci.' or dept_name = 'Comp. Sci.'

-- 1. Comp. Sci. 학과에 소속된 어떤 학생보다 많은 학점을 이수한 학생의 이름을 검색.
select distinct S1.name, S1.dept_name, S1.tot_cred
from student as S1, student as S2
where (S1.tot_cred > S2.tot_cred) and (S2.dept_name = 'Comp. Sci.')

-- 2. Comp. Sci. 학과에 소속된 어떤 학생보다 많은 학점을 이수한 학생들 가운데 Comp. Sci. 학과에 소속되지 않은 학생들의 수를 학과별로 검색.
select S1.dept_name, count(distinct S1.ID) as student_cnt
from student as S1, student as S2
where (S1.tot_cred > S2.tot_cred) and (S2.dept_name = 'Comp. Sci.')
group by S1.dept_name
having S1.dept_name != 'Comp. Sci.'

-- 3. Accounting 학과에 소속된 학생들 중 이름이 Ch 로 시작하는 학생들에 대한 모든 정보를 검색.
select *
from student
where (dept_name = 'Accounting') and (name like 'Ch%')

-- 4. 예산이 600000 이상 800000 이하인 학과의 학과명과 예산을 예산값 내림차순 기준으로 검색.
select dept_name, budget
from department
where budget between 600000 and 800000
order by budget desc

-- 5. 2006학년도 봄학기에는 강의를 맡았지만 2006학년도 가을학기에는 강의를 맡지 않은 교수들의 교수번호를 검색.
select ID
from teaches
where semester = 'Spring' and year = 2006
except
select ID
from teaches
where semester = 'Fall' and year = 2009

-- 6. 아직 연봉이 정해지지 않은(연봉이 NULL을 가지는) 교수들의 수를 학과별로 검색
select dept_name, count(*) as instructor_cnt
from instructor
where salary is null
group by dept_name

-- 7. 학년도, 학기 기준으로 수강신청하여 이수한 학생 수를 검색
-- (수강 신청 후 아직 이수완료하지 않은 경우 grade는 null을 가짐. grade 가 'F'인 경우 해당 강좌를 이수하지 않은 것이 됨.)
-- 이거 시발 왜 F 받은새끼가 없어
select count(distinct ID) as std_cnt, year, semester
from takes
where (grade is not null) and (grade != 'F')
group by year, semester
order by year, semester desc

-- 8. 학과별 지도학생 수를 검색
select instructor.dept_name, count(s_id) as std_cnt
from advisor, instructor
where i_id = ID
group by instructor.dept_name

-- 9. 50명 이상 학생들을 지도하는 교수들의 교수번호와 지도학생 수를 검색
select i_id, count(s_id) as s_cnt
from advisor, instructor
where i_id = ID
group by i_id
having count(s_id) >= 50

-- 10. 학년도, 강의실(building, room_number) 기준으로 해당 학년도, 강의실에서 진행된 강좌에 참여한 학생 수를 검색
select * from section order by course_id

select takes.year, takes.semester, building, room_number, count(distinct ID) as std_cnt, takes.course_id, takes.sec_id
from section, takes
where (takes.course_id = section.course_id) and (takes.semester = section.semester) and (section.sec_id = takes.sec_id)
group by takes.year, building, room_number, takes.course_id, takes.sec_id, takes.semester
order by takes.course_id

select year, semester, count(ID) as std_cnt, course_id, sec_id
from takes
group by course_id, year, semester, sec_id
order by course_id asc
