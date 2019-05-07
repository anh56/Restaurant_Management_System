USE [master]
GO

/* For security reasons the login is created disabled and with a random password. */ /*real password is 12300*/
/****** Object:  Login [ANH]    Script Date: 5/7/2019 1:23:10 PM ******/
CREATE LOGIN [ANH] WITH PASSWORD=N'p0tjnGKID00KUqeoej1SywEscDjV8ANrpiA+5TCl+Rg=', DEFAULT_DATABASE=[master], DEFAULT_LANGUAGE=[us_english], CHECK_EXPIRATION=OFF, CHECK_POLICY=ON
GO

ALTER LOGIN [ANH] DISABLE
GO

ALTER SERVER ROLE [sysadmin] ADD MEMBER [ANH]
GO

ALTER SERVER ROLE [securityadmin] ADD MEMBER [ANH]
GO

ALTER SERVER ROLE [serveradmin] ADD MEMBER [ANH]
GO

ALTER SERVER ROLE [setupadmin] ADD MEMBER [ANH]
GO

ALTER SERVER ROLE [processadmin] ADD MEMBER [ANH]
GO

ALTER SERVER ROLE [diskadmin] ADD MEMBER [ANH]
GO

ALTER SERVER ROLE [dbcreator] ADD MEMBER [ANH]
GO

ALTER SERVER ROLE [bulkadmin] ADD MEMBER [ANH]
GO

