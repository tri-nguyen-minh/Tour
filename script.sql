USE [master]
GO
ALTER DATABASE [TravelDB] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TravelDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TravelDB] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [TravelDB] SET ANSI_NULLS OFF
GO
ALTER DATABASE [TravelDB] SET ANSI_PADDING OFF
GO
ALTER DATABASE [TravelDB] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [TravelDB] SET ARITHABORT OFF
GO
ALTER DATABASE [TravelDB] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [TravelDB] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [TravelDB] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [TravelDB] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [TravelDB] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [TravelDB] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [TravelDB] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [TravelDB] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [TravelDB] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [TravelDB] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [TravelDB] SET  DISABLE_BROKER
GO
ALTER DATABASE [TravelDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [TravelDB] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [TravelDB] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [TravelDB] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [TravelDB] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [TravelDB] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [TravelDB] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [TravelDB] SET  READ_WRITE
GO
ALTER DATABASE [TravelDB] SET RECOVERY SIMPLE
GO
ALTER DATABASE [TravelDB] SET  MULTI_USER
GO
ALTER DATABASE [TravelDB] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [TravelDB] SET DB_CHAINING OFF
GO
USE [TravelDB]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 06/22/2020 00:09:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Status](
	[statusId] [int] NOT NULL,
	[status] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Status] ([statusId], [status]) VALUES (1, N'Active')
INSERT [dbo].[Status] ([statusId], [status]) VALUES (2, N'Inactive')
/****** Object:  Table [dbo].[Roles]    Script Date: 06/22/2020 00:09:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Roles](
	[roleId] [int] NOT NULL,
	[role] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Roles] ([roleId], [role]) VALUES (1, N'admin')
INSERT [dbo].[Roles] ([roleId], [role]) VALUES (2, N'user')
/****** Object:  Table [dbo].[Discount]    Script Date: 06/22/2020 00:09:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Discount](
	[id] [varchar](50) NOT NULL,
	[discountCode] [varchar](50) NOT NULL,
	[value] [int] NOT NULL,
	[expireDate] [date] NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Discount] ([id], [discountCode], [value], [expireDate]) VALUES (N'D01', N'FIRSTD', 20, CAST(0x84410B00 AS Date))
INSERT [dbo].[Discount] ([id], [discountCode], [value], [expireDate]) VALUES (N'D02', N'BLMALWAYS', 25, CAST(0xA2410B00 AS Date))
INSERT [dbo].[Discount] ([id], [discountCode], [value], [expireDate]) VALUES (N'NONE', N'NONE', 0, NULL)
/****** Object:  Table [dbo].[Users]    Script Date: 06/22/2020 00:09:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[id] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[name] [varchar](max) NOT NULL,
	[roleId] [int] NOT NULL,
	[statusId] [int] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Users] ([id], [password], [name], [roleId], [statusId]) VALUES (N'admin', N'admin', N'Admin', 1, 1)
INSERT [dbo].[Users] ([id], [password], [name], [roleId], [statusId]) VALUES (N'austin316', N'austin316', N'Austin', 2, 1)
INSERT [dbo].[Users] ([id], [password], [name], [roleId], [statusId]) VALUES (N'user1', N'123456', N'John', 2, 1)
INSERT [dbo].[Users] ([id], [password], [name], [roleId], [statusId]) VALUES (N'user2', N'123456', N'Jimmy Crownley', 2, 2)
/****** Object:  Table [dbo].[Tours]    Script Date: 06/22/2020 00:09:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Tours](
	[tourId] [varchar](50) NOT NULL,
	[tourName] [varchar](max) NOT NULL,
	[destination] [varchar](max) NOT NULL,
	[fromDate] [date] NOT NULL,
	[toDate] [date] NOT NULL,
	[price] [int] NOT NULL,
	[quota] [int] NOT NULL,
	[image] [varchar](max) NOT NULL,
	[dateImport] [date] NOT NULL,
	[statusId] [int] NOT NULL,
 CONSTRAINT [PK_Tours] PRIMARY KEY CLUSTERED 
(
	[tourId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T1', N'Peru Adventure', N'Peru', CAST(0xC1410B00 AS Date), CAST(0xC8410B00 AS Date), 1200, 16, N'img/tourIMG/MachuPicchu.jpg', CAST(0x2B410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T10', N'Antarctic Whale Journey', N'Antarctica', CAST(0x33420B00 AS Date), CAST(0x3C420B00 AS Date), 4990, 200, N'img/tourIMG/Antarctica.jpg', CAST(0x2B410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T11', N'Jewels of the Russian Arctic', N'Arctic', CAST(0x89420B00 AS Date), CAST(0x9D420B00 AS Date), 12490, 130, N'img/tourIMG/Arctic.jpg', CAST(0x2B410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T12', N'Italy Experience', N'Italy', CAST(0x4E410B00 AS Date), CAST(0x59410B00 AS Date), 3085, 16, N'img/tourIMG/Venice.jpg', CAST(0x2D410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T13', N'Beautiful Vietnam!', N'Vietnam', CAST(0x94410B00 AS Date), CAST(0x9E410B00 AS Date), 500, 20, N'img/tourIMG/Halong.jpg', CAST(0x35410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T14', N'Argentina & Brazil Adventure', N'Argentina & Brazil', CAST(0xE3410B00 AS Date), CAST(0xF0410B00 AS Date), 2970, 12, N'img/tourIMG/TBA.jpg', CAST(0x3C410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T15', N'Morocco Uncovered', N'Morocco', CAST(0x93410B00 AS Date), CAST(0xA1410B00 AS Date), 1680, 14, N'img/tourIMG/Morocco.jpg', CAST(0x3C410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T16', N'Rio Carnival', N'Brazil', CAST(0x26420B00 AS Date), CAST(0x31420B00 AS Date), 2050, 15, N'img/tourIMG/Brazil.jpg', CAST(0x3D410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T17', N'Cycle Vietnam', N'Vietnam', CAST(0x7F420B00 AS Date), CAST(0x8F420B00 AS Date), 2115, 20, N'img/tourIMG/HoChiMinh.jpg', CAST(0x3D410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T2', N'Journey Across Vietnam', N'Vietnam', CAST(0x65410B00 AS Date), CAST(0x70410B00 AS Date), 1230, 16, N'img/tourIMG/Halong.jpg', CAST(0x2B410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T3', N'Scenic Vietnam', N'Vietnam', CAST(0x57410B00 AS Date), CAST(0x67410B00 AS Date), 1690, 20, N'img/tourIMG/Hue.jpg', CAST(0x2B410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T4', N'Egypt Experience', N'Egypt', CAST(0xA4410B00 AS Date), CAST(0xAE410B00 AS Date), 2780, 12, N'img/tourIMG/Giza.jpg', CAST(0x2B410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T5', N'Jordan & Egypt Uncovered', N'Egypt & Jordan', CAST(0x3B410B00 AS Date), CAST(0x51410B00 AS Date), 2935, 16, N'img/tourIMG/EgyptJordan.jpg', CAST(0x2B410B00 AS Date), 2)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T6', N'Express Egypt', N'Egypt', CAST(0x2F410B00 AS Date), CAST(0x3A410B00 AS Date), 1890, 12, N'img/tourIMG/Egypt.jpg', CAST(0x2B410B00 AS Date), 2)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T7', N'Egypt Adventure', N'Egypt', CAST(0x6E410B00 AS Date), CAST(0x76410B00 AS Date), 1150, 12, N'img/tourIMG/Cairo.jpg', CAST(0x2B410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T8', N'Sail Greece', N'Greece', CAST(0x84420B00 AS Date), CAST(0x1E410B00 AS Date), 1590, 11, N'img/tourIMG/Mykonos.jpg', CAST(0x2B410B00 AS Date), 1)
INSERT [dbo].[Tours] ([tourId], [tourName], [destination], [fromDate], [toDate], [price], [quota], [image], [dateImport], [statusId]) VALUES (N'T9', N'Greece Discovery', N'Greece', CAST(0x81420B00 AS Date), CAST(0x88420B00 AS Date), 1790, 12, N'img/tourIMG/Athens.jpg', CAST(0x2B410B00 AS Date), 1)
/****** Object:  Table [dbo].[Booking]    Script Date: 06/22/2020 00:09:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Booking](
	[id] [varchar](50) NOT NULL,
	[tourId] [varchar](50) NOT NULL,
	[userId] [varchar](50) NOT NULL,
	[bookingDate] [date] NOT NULL,
	[amount] [int] NOT NULL,
	[totalPrice] [float] NOT NULL,
	[discountId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Booking] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B1', N'T1', N'user1', CAST(0x30410B00 AS Date), 3, 360, N'NONE')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B10', N'T4', N'user1', CAST(0x39410B00 AS Date), 1, 2780, N'NONE')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B2', N'T5', N'user1', CAST(0x30410B00 AS Date), 5, 11740, N'D01')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B3', N'T5', N'user2', CAST(0x30410B00 AS Date), 11, 32285, N'NONE')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B4', N'T12', N'user2', CAST(0x30410B00 AS Date), 2, 4627.5, N'D02')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B5', N'T10', N'user2', CAST(0x30410B00 AS Date), 4, 14970, N'D02')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B6', N'T1', N'user1', CAST(0x30410B00 AS Date), 13, 15600, N'NONE')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B7', N'T3', N'user1', CAST(0x31410B00 AS Date), 3, 5070, N'NONE')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B8', N'T10', N'user1', CAST(0x31410B00 AS Date), 81, 404190, N'NONE')
INSERT [dbo].[Booking] ([id], [tourId], [userId], [bookingDate], [amount], [totalPrice], [discountId]) VALUES (N'B9', N'T12', N'user1', CAST(0x32410B00 AS Date), 7, 21595, N'NONE')
/****** Object:  ForeignKey [FK_Users_Role]    Script Date: 06/22/2020 00:09:36 ******/
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Role] FOREIGN KEY([roleId])
REFERENCES [dbo].[Roles] ([roleId])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Role]
GO
/****** Object:  ForeignKey [FK_Users_Status]    Script Date: 06/22/2020 00:09:36 ******/
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Status] FOREIGN KEY([statusId])
REFERENCES [dbo].[Status] ([statusId])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Status]
GO
/****** Object:  ForeignKey [FK_Tours_Status]    Script Date: 06/22/2020 00:09:36 ******/
ALTER TABLE [dbo].[Tours]  WITH CHECK ADD  CONSTRAINT [FK_Tours_Status] FOREIGN KEY([statusId])
REFERENCES [dbo].[Status] ([statusId])
GO
ALTER TABLE [dbo].[Tours] CHECK CONSTRAINT [FK_Tours_Status]
GO
/****** Object:  ForeignKey [FK_Booking_Discount]    Script Date: 06/22/2020 00:09:36 ******/
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_Discount] FOREIGN KEY([discountId])
REFERENCES [dbo].[Discount] ([id])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_Discount]
GO
/****** Object:  ForeignKey [FK_Booking_Tours]    Script Date: 06/22/2020 00:09:36 ******/
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_Tours] FOREIGN KEY([tourId])
REFERENCES [dbo].[Tours] ([tourId])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_Tours]
GO
/****** Object:  ForeignKey [FK_Booking_Users]    Script Date: 06/22/2020 00:09:36 ******/
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD  CONSTRAINT [FK_Booking_Users] FOREIGN KEY([userId])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Booking] CHECK CONSTRAINT [FK_Booking_Users]
GO
