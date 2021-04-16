-- �˻� �䱸 6���� ���� ó���� ���� SQL��.
-- �а����� 'Pol. Sci.' �̰ų� 'Comp. Sci.' �� �������� ������ null ������ �ٲ�.
update instructor
set salary = null
where dept_name = 'Pol. Sci.' or dept_name = 'Comp. Sci.'

-- 1. Comp. Sci. �а��� �Ҽӵ� � �л����� ���� ������ �̼��� �л��� �̸��� �˻�.
select distinct S1.name, S1.dept_name, S1.tot_cred
from student as S1, student as S2
where (S1.tot_cred > S2.tot_cred) and (S2.dept_name = 'Comp. Sci.')

-- 2. Comp. Sci. �а��� �Ҽӵ� � �л����� ���� ������ �̼��� �л��� ��� Comp. Sci. �а��� �Ҽӵ��� ���� �л����� ���� �а����� �˻�.
select S1.dept_name, count(distinct S1.ID) as student_cnt
from student as S1, student as S2
where (S1.tot_cred > S2.tot_cred) and (S2.dept_name = 'Comp. Sci.')
group by S1.dept_name
having S1.dept_name != 'Comp. Sci.'

-- 3. Accounting �а��� �Ҽӵ� �л��� �� �̸��� Ch �� �����ϴ� �л��鿡 ���� ��� ������ �˻�.
select *
from student
where (dept_name = 'Accounting') and (name like 'Ch%')

-- 4. ������ 600000 �̻� 800000 ������ �а��� �а���� ������ ���갪 �������� �������� �˻�.
select dept_name, budget
from department
where budget between 600000 and 800000
order by budget desc

-- 5. 2006�г⵵ ���б⿡�� ���Ǹ� �þ����� 2006�г⵵ �����б⿡�� ���Ǹ� ���� ���� �������� ������ȣ�� �˻�.
select ID
from teaches
where semester = 'Spring' and year = 2006
except
select ID
from teaches
where semester = 'Fall' and year = 2009

-- 6. ���� ������ �������� ����(������ NULL�� ������) �������� ���� �а����� �˻�
select dept_name, count(*) as instructor_cnt
from instructor
where salary is null
group by dept_name

-- 7. �г⵵, �б� �������� ������û�Ͽ� �̼��� �л� ���� �˻�
-- (���� ��û �� ���� �̼��Ϸ����� ���� ��� grade�� null�� ����. grade �� 'F'�� ��� �ش� ���¸� �̼����� ���� ���� ��.)
-- �̰� �ù� �� F ���������� ����
select count(distinct ID) as std_cnt, year, semester
from takes
where (grade is not null) and (grade != 'F')
group by year, semester
order by year, semester desc

-- 8. �а��� �����л� ���� �˻�
select instructor.dept_name, count(s_id) as std_cnt
from advisor, instructor
where i_id = ID
group by instructor.dept_name

-- 9. 50�� �̻� �л����� �����ϴ� �������� ������ȣ�� �����л� ���� �˻�
select i_id, count(s_id) as s_cnt
from advisor, instructor
where i_id = ID
group by i_id
having count(s_id) >= 50

-- 10. �г⵵, ���ǽ�(building, room_number) �������� �ش� �г⵵, ���ǽǿ��� ����� ���¿� ������ �л� ���� �˻�
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
