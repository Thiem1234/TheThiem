<?php 
	require "connect.php";

	class BaiHat{
		function BaiHat($idBaiHat, $tenBaiHat, $hinhBaiHat, $caSi, $linkBaiHat, $luotThich){
			$this->IdBaiHat = $idBaiHat;
			$this->TenBaiHat = $tenBaiHat;
			$this->HinhBaiHat = $hinhBaiHat;
			$this->Casi = $caSi;
			$this->LinkBaiHat = $linkBaiHat;
			$this->LuotThich = $luotThich;
		}
	}
	$mangCaKhuc = array();
	if (isset($_POST['tukhoa'])) {
		$tuKhoa = $_POST['tukhoa'];
		$query = "SELECT * FROM baihat WHERE lower(TenBaiHat) LIKE '%$tuKhoa%'";
		$data = mysqli_query($conn, $query);
		while ($row = mysqli_fetch_assoc($data)) {
			array_push($mangCaKhuc, new BaiHat($row['IdBaiHat']
											,$row['TenBaiHat']
											,$row['HinhBaiHat']
											,$row['Casi']
											,$row['LinkBaiHat']
											,$row['LuotThich']));
		}
		echo json_encode($mangCaKhuc);
	}

	
	

 ?>