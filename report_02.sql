update instructor
set salary = null
where dept_name = 'Pol. Sci.' or dept_name = 'Comp. Sci.'

-- 1. Comp. Sci. �а��� �Ҽӵ� � �л����� ���� ������ �̼��� �л��� �̸��� �˻�.

-- 2. Comp. Sci. �а��� �Ҽӵ� � �л����� ���� ������ �̼��� �л��� ��� Comp. Sci. �а��� �Ҽӵ��� ���� �л����� ���� �а����� �˻�.

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
select *
from teaches
where semester = 'Spring' and year = 2006

select *
from teaches
where semester = 'Fall' and year = 2009


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


-- 8. �а��� �����л� ���� �˻� -> �´��� �ƴ��� ��
select instructor.dept_name, count(s_id) as s_cnt
from advisor, instructor
where i_id = ID
group by instructor.dept_name

select dept_name, count(*) as cnt
from student
group by dept_name

-- 9. 50�� �̻� �л����� �����ϴ� �������� ������ȣ�� �����л� ���� �˻�.

-- 10. �г⵵, ���ǽ�(building, room_number) �������� �ش� �г⵵, ���ǽǿ��� ����� ���¿� ������ �л� ���� �˻�.
