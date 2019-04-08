<?php 
	require "connect.php";

	class BaiHat{
		function BaiHat($idBaiHat, $tenBaiHat, $hinhBaiHat, $caSi, $linkBaiHat,$luotThich){
			$this->IdBaiHat = $idBaiHat;
			$this->TenBaiHat = $tenBaiHat;
			$this->HinhBaiHat = $hinhBaiHat;
			$this->Casi = $caSi;
			$this->LinkBaiHat = $linkBaiHat;
			$this->LuotThich = $luotThich;
		}
	}
	$arrayDanhSachBaiHat = array();
	
	if (isset($_POST['idtheloai'])) {
		$idTheLoai = $_POST['idtheloai'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idTheLoai',IdTheLoai)";
	}
	if (isset($_POST['idplaylist'])) {
		$idPlayList = $_POST['idplaylist'];
		$query = "SELECT * FROM baihat WHERE FIND_IN_SET('$idPlayList',IdPlayList)";
	}

	if (isset($_POST['idquangcao'])) {
		$idQuangCao = $_POST['idquangcao'];
		$queryQuangCao = "SELECT * FROM quangcao WHERE Id  = '$idQuangCao'";
		$dataQuangCao = mysqli_query($conn, $queryQuangCao);
		$rowQuangCao = mysqli_fetch_assoc($dataQuangCao);
		$id  = $rowQuangCao['IdBaiHat'];
		$query = "SELECT * FROM baihat WHERE IdBaiHat = '$id'";
	}
	
	$data = mysqli_query($conn,$query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayDanhSachBaiHat, new BaiHat($row['IdBaiHat'],
													$row['TenBaiHat'],
													$row['HinhBaiHat'],
													$row['Casi'],
													$row['LinkBaiHat'],
													$row['LuotThich']));
	}
	echo json_encode($arrayDanhSachBaiHat);
 ?>