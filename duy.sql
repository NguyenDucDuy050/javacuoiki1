-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2025 at 05:08 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `duy`
--

-- --------------------------------------------------------

--
-- Table structure for table `chisodien`
--

CREATE TABLE `chisodien` (
  `MaKH` varchar(20) NOT NULL,
  `MaThang` varchar(20) NOT NULL,
  `ChiSoCu` int(100) NOT NULL,
  `ChiSoMoi` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chisodien`
--

INSERT INTO `chisodien` (`MaKH`, `MaThang`, `ChiSoCu`, `ChiSoMoi`) VALUES
('kh01', 'thang3', 123, 126),
('kh02', 'thang2', 111, 222),
('kh03', 'thang3', 111, 121),
('kh04', 'thang1', 100, 2000),
('kh06', 'thang1', 100, 200);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(20) NOT NULL,
  `TenKH` varchar(20) NOT NULL,
  `GioiTinh` varchar(10) DEFAULT NULL,
  `NgaySinh` date NOT NULL,
  `CMND` int(40) DEFAULT NULL,
  `SDT` int(100) DEFAULT NULL,
  `NgayDangki` date DEFAULT NULL,
  `DiaChi` varchar(100) DEFAULT NULL,
  `LoaiDien` varchar(20) DEFAULT NULL,
  `TrangThai` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `GioiTinh`, `NgaySinh`, `CMND`, `SDT`, `NgayDangki`, `DiaChi`, `LoaiDien`, `TrangThai`) VALUES
('kh01', 'Nguyễn Đức Duy', 'Male', '2006-10-11', 88729873, 334909478, '2006-10-11', 'Quảng Trị', 'Sinh Hoat', 'Đã thanh toán'),
('kh02', 'teo', 'Male', '2000-10-11', 2093820, 302802823, '1999-09-11', 'ee', 'Sinh Hoat', NULL),
('kh03', 'Nguyen Van A', 'Male', '2006-10-11', 8883028, 123456789, '1999-01-11', 'Đà Nẵng', 'Sinh Hoat', NULL),
('kh04', 'Nguyen Thi B', 'Female', '1999-02-01', 246456453, 987654321, '1000-02-02', 'Linh An', 'Sinh Hoat', 'Đã thanh toán'),
('kh06', 'Thanh', 'Male', '1999-10-11', 8398323, 123456789, '2000-10-11', 'DN', 'Sinh Hoat', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chisodien`
--
ALTER TABLE `chisodien`
  ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
