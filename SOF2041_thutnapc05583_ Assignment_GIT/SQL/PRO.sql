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

