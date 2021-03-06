USE [Social_Network]
GO
/****** Object:  Table [dbo].[accountTBL]    Script Date: 3/23/2021 2:53:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[accountTBL](
	[email_user] [nchar](100) NOT NULL,
	[name_user] [nchar](100) NOT NULL,
	[password_user] [nchar](100) NULL,
	[role_user] [nchar](20) NULL,
	[status_user] [nchar](10) NULL,
 CONSTRAINT [PK_accountTBL_1] PRIMARY KEY CLUSTERED 
(
	[email_user] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[articleTBL]    Script Date: 3/23/2021 2:53:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[articleTBL](
	[title_article] [nchar](100) NOT NULL,
	[description_article] [nchar](1000) NULL,
	[image_article] [varchar](255) NULL,
	[date_article] [nchar](100) NULL,
	[path] [varchar](255) NULL,
	[id_article] [int] IDENTITY(1,1) NOT NULL,
	[poster] [nchar](100) NULL,
	[status_article] [nchar](10) NULL,
 CONSTRAINT [PK_articleTBL_1] PRIMARY KEY CLUSTERED 
(
	[id_article] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[interaction_accountTBL]    Script Date: 3/23/2021 2:53:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[interaction_accountTBL](
	[id_interaction_account] [int] IDENTITY(1,1) NOT NULL,
	[id_interaction] [int] NOT NULL,
	[id_article] [int] NOT NULL,
	[time_interaction] [nchar](100) NULL,
 CONSTRAINT [PK_interaction_accountTBL] PRIMARY KEY CLUSTERED 
(
	[id_interaction] ASC,
	[id_article] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[interaction_articleTBL]    Script Date: 3/23/2021 2:53:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[interaction_articleTBL](
	[id_interaction] [int] IDENTITY(1,1) NOT NULL,
	[id_article] [int] NULL,
	[comments] [nchar](1000) NULL,
	[likes] [int] NULL,
	[dislikes] [int] NULL,
	[date] [nchar](1000) NULL,
	[poster] [nchar](100) NULL,
	[email_user] [nchar](100) NULL,
	[color] [nchar](20) NULL,
 CONSTRAINT [PK_interaction_articleTBL] PRIMARY KEY CLUSTERED 
(
	[id_interaction] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[notitication_accountTBL]    Script Date: 3/23/2021 2:53:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[notitication_accountTBL](
	[id_notifcation] [int] IDENTITY(1,1) NOT NULL,
	[name_notification] [nchar](100) NULL,
	[date_notification] [nchar](100) NULL,
	[type_interaction] [nchar](20) NULL,
	[content_notification] [nchar](1000) NULL,
	[id_article] [int] NULL,
	[email_user] [nchar](100) NULL,
 CONSTRAINT [PK_notitication_accountTBLl] PRIMARY KEY CLUSTERED 
(
	[id_notifcation] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[accountTBL] ([email_user], [name_user], [password_user], [role_user], [status_user]) VALUES (N'admin@gmail.com                                                                                     ', N'admin                                                                                               ', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b                                    ', N'admin               ', N'Active    ')
INSERT [dbo].[accountTBL] ([email_user], [name_user], [password_user], [role_user], [status_user]) VALUES (N'hoangnmse141110@fpt.edu.vn                                                                          ', N'Minh Hoang                                                                                          ', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b                                    ', N'member              ', N'Active    ')
INSERT [dbo].[accountTBL] ([email_user], [name_user], [password_user], [role_user], [status_user]) VALUES (N'minh@gmail.com                                                                                      ', N'Quang minh                                                                                          ', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b                                    ', N'member              ', N'New       ')
INSERT [dbo].[accountTBL] ([email_user], [name_user], [password_user], [role_user], [status_user]) VALUES (N'nguyenlase141102@fpt.edu.vn                                                                         ', N'Le Anh Nguyen                                                                                       ', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b                                    ', N'member              ', N'Active    ')
INSERT [dbo].[accountTBL] ([email_user], [name_user], [password_user], [role_user], [status_user]) VALUES (N'ntdl123@gmail.com                                                                                   ', N'Nguyen tran da ly                                                                                   ', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b                                    ', N'member              ', N'Active    ')
INSERT [dbo].[accountTBL] ([email_user], [name_user], [password_user], [role_user], [status_user]) VALUES (N'thaichau@gmail.com                                                                                  ', N'Tran nhu thai chau                                                                                  ', N'6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b                                    ', N'member              ', N'Active    ')
GO
SET IDENTITY_INSERT [dbo].[articleTBL] ON 

INSERT [dbo].[articleTBL] ([title_article], [description_article], [image_article], [date_article], [path], [id_article], [poster], [status_article]) VALUES (N'Ã¢                                                                                                  ', N'Ã¡d                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ', N'quiz-2-.jpg', N'Mar-23-2021                                                                                         ', N'', 155, N'Le Anh Nguyen                                                                                       ', N'Active    ')
SET IDENTITY_INSERT [dbo].[articleTBL] OFF
GO
ALTER TABLE [dbo].[interaction_accountTBL]  WITH CHECK ADD  CONSTRAINT [FK_interaction_accountTBL_articleTBL] FOREIGN KEY([id_article])
REFERENCES [dbo].[articleTBL] ([id_article])
GO
ALTER TABLE [dbo].[interaction_accountTBL] CHECK CONSTRAINT [FK_interaction_accountTBL_articleTBL]
GO
ALTER TABLE [dbo].[interaction_accountTBL]  WITH CHECK ADD  CONSTRAINT [FK_interaction_accountTBL_interaction_articleTBL] FOREIGN KEY([id_interaction])
REFERENCES [dbo].[interaction_articleTBL] ([id_interaction])
GO
ALTER TABLE [dbo].[interaction_accountTBL] CHECK CONSTRAINT [FK_interaction_accountTBL_interaction_articleTBL]
GO
ALTER TABLE [dbo].[interaction_articleTBL]  WITH CHECK ADD  CONSTRAINT [FK_interaction_articleTBL_accountTBL] FOREIGN KEY([email_user])
REFERENCES [dbo].[accountTBL] ([email_user])
GO
ALTER TABLE [dbo].[interaction_articleTBL] CHECK CONSTRAINT [FK_interaction_articleTBL_accountTBL]
GO
ALTER TABLE [dbo].[notitication_accountTBL]  WITH CHECK ADD  CONSTRAINT [FK_notitication_accountTBL_accountTBL1] FOREIGN KEY([email_user])
REFERENCES [dbo].[accountTBL] ([email_user])
GO
ALTER TABLE [dbo].[notitication_accountTBL] CHECK CONSTRAINT [FK_notitication_accountTBL_accountTBL1]
GO
