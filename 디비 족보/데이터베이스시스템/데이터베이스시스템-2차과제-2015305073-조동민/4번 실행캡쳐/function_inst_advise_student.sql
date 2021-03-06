USE [LargeDB]
GO
/****** Object:  UserDefinedFunction [dbo].[inst_advise_student]    Script Date: 2020-06-04 오전 11:16:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


ALTER function [dbo].[inst_advise_student](@inst_name varchar(20))
returns integer
as
begin
	declare @s_count integer;
	select @s_count = count(*)
	from instructor, advisor
	where instructor.ID = advisor.i_ID and instructor.name = @inst_name
	return(@s_count);
end

