create database EDUSYS

use EDUSYS

CREATE TABLE NhanVien(
MaNV NVARCHAR(50) NOT NULL,
MatKhau NVARCHAR(50) NOT NULL,
HoTen NVARCHAR(50) NOT NULL,
VaiTro BIT NOT NULL,
PRIMARY KEY(MaNV) )

CREATE TABLE CHUYENDE(
MaCD NCHAR(5) NOT NULL,
TenCD NVARCHAR(50) NOT NULL,
HocPhi FLOAT NOT NULL,
ThoiLuong INT NOT NULL,
Hinh NVARCHAR(50) NOT NULL,
MoTa NVARCHAR(255) NOT NULL,
PRIMARY KEY (MaCD))


CREATE TABLE NGUOIHOC (
MaNH NCHAR(7) NOT NULL,
HoTen NVARCHAR(50) NOT NULL,
GioiTinh BIT DEFAULT 1,
NgaySinh DATE NOT NULL,
DienThoai NVARCHAR(24) NOT NULL,
EMail NVARCHAR(50) NOT NULL,
GhiChu NVARCHAR(255) NOT NULL,
NgayDK DATE DEFAULT GETDATE(),
MaNV NVARCHAR(50) NOT NULL,
PRIMARY KEY (MaNH),
FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV) ON DELETE NO ACTION ON UPDATE CASCADE,
);

CREATE TABLE KHOAHOC (
MaKH INT IDENTITY (1,1) NOT NULL,
MaCD NCHAR(5) NOT NULL,
HocPhi FLOAT NOT NULL,
ThoiLuong INT NOT NULL,
NgayKG DATE NOT NULL,
GhiChu NVARCHAR(50) NULL,
MaNV NVARCHAR(50) NOT NULL,
NgayTao DATE NOT NULL,
PRIMARY KEY(MaKH),
FOREIGN KEY (MaCD) REFERENCES CHUYENDE(MaCD) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (MaNV) REFERENCES NHANVIEN(MaNV) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE HOCVIEN(
MaHV INT IDENTITY (1,1) PRIMARY KEY,
MaKH INT NOT NULL,
MaNH NCHAR(7) NOT NULL,
DIEM FLOAT DEFAULT - 1,
FOREIGN KEY (MaKH) REFERENCES KHOAHOC(MaKH),
FOREIGN KEY (MaNH) REFERENCES NGUOIHOC(MaNH) ON DELETE NO ACTION ON UPDATE CASCADE,
);

CREATE PROC sp_BangDiem(@MaKH int)
AS BEGIN 
	SELECT 
		hv.MaNH,
		nh.HoTen,
		hv.DIEM
	FROM HOCVIEN hv JOIN NGUOIHOC nh 
	ON nh.MaNH = hv.MaNH
	WHERE hv.MaKH = @MaKH
	ORDER BY hv.DIEM DESC
END

CREATE PROC sp_LuongNguoiHoc
AS BEGIN 
	SELECT 
		YEAR(NgayDK) Nam,
		COUNT(*) SoLuong,
		MIN (NgayDK) DauTien,
		Max(NgayDK) CuoiCung
	FROM NGUOIHOC
	GROUP BY YEAR(NgayDK)
END

CREATE PROC sp_DiemChuyenDe
AS BEGIN 
	SELECT 
		TenCD ChuyenDe,
		COUNT(MAHV) SoHV,
		MIN(DIEM) ThapNhat,
		MAX(DIEM) CaoNhat,
		AVG(DIEM) TrungBinh
	FROM KHOAHOC kh 
		JOIN HOCVIEN hv ON kh.MaKH = hv.MaKH
		JOIN CHUYENDE cd ON cd.MaCD = kh.MaCD
	GROUP BY TenCD
END


CREATE PROC sp_DoanhThu(@Year int)
AS BEGIN 
	SELECT 
		TenCD ChuyenDe,
		COUNT(DISTINCT kh.MaKH) SoKH,
		COUNT (hv.MaHV) SoHV,
		SUM(kh.HocPhi) DoanhThu,
		MIN(kh.HocPhi) ThapNhat,
		MAX (kh.HocPhi) CaoNhat,
		AVG(kh.HocPhi) TrungBinh
	FROM KhoaHoc kh
		JOIN HocVien hv ON kh.MaKh = hv.MaKH
		JOIN ChuyenDe cd ON cd.MaCD = kh.MaCD
	WHERE YEAR(NgayKG) = @Year
	GROUP BY TenCD
END
