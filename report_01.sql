-- ����1. �а����� 'Physics'�� �а��� �Ҽӵ� �л����� �̸��� �л� �� �� �̼����� �˻�
select name, tot_cred
from student
where dept_name = 'Physics'


-- ����2. �а����� 'Physics'�� �а��� ��ϴ� ������ �� �̼������� 3���� �̻��� ��������� �������� �̼����� �˻�
select title, credits
from course
where (dept_name = 'Physics') and (credits >= 3)

-- ���� 3. ������ 100000 �̻��� �а��� �Ҽӵ� �������� �̸��� ������ ���� �˻�
-- department.dept_name = instructor.dept_name => �ڿ� ���� ����
select name, salary, instructor.dept_name
from instructor, department
where (department.budget >= 100000) and (department.dept_name = instructor.dept_name)

-- course �����̼�
select * from course

-- instructor �����̼�
select* from instructor

-- teaches �����̼�
select * from teaches where (semester = 'Fall') and (year = 2009)

-- ����4. 2009�г⵵ ����(Fall) �б⿡ ���Ǹ� ����Ͽ��� �������� �̸��� �Ҽ��а� �˻�
-- instructor.ID = teaches.ID => �ڿ� ���� ����
select instructor.name as instructor_name, instructor.dept_name, semester, year
from instructor, teaches
where (semester = 'Fall') and (year = 2009) and (instructor.ID = teaches.ID)

-- advisor �����̼�
select * from advisor

-- ����5. Physics �а��� �Ҽӵ� ������κ��� �����޴� �л����� �̸� �˻�
-- (i_ID = instructor.ID) and (s_ID = student.ID) => �ڿ� ���� ����
select student.name as student_name, instructor.name as instructor_name, student.dept_name as s_dept_name, instructor.dept_name as i_dept_name
from advisor, instructor, student
where (i_ID = instructor.ID) and (s_ID = student.ID) and (instructor.dept_name = 'Physics')

-- �����а� ���� ����
select name from instructor where dept_name = 'Physics'
