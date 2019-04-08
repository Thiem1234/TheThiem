<?php 
	require "connect.php";

	class BaiHat{
		function BaiHat($idBaiHat, $tenBaiHat, $hinhBaiHat, $caSi, $linkBaiHat, $luotThich){
			$this->IdBaiHat = $idBaiHat;
			$this->TenBaiHat = $tenBaiHat;
			$this->HinhBaiHat = $hinhBaiHat;
			$this->CaSi = $caSi;
			$this->LinkBaiHat = $linkBaiHat;
			$this->LuotThich = $luotThich;
		}
	}
	$arrayCaSi = array();
	$query = "SELECT * FROM baihat ORDER BY LuotThich DESC LIMIT 5";
	$data = mysqli_query($conn, $query);
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayCaSi, new BaiHat($row['IdBaiHat']
										,$row['TenBaiHat']
										,$row['HinhBaiHat']
										,$row['Casi']
										,$row['LinkBaiHat']
										,$row['LuotThich']));
	}
	echo json_encode($arrayCaSi);

 ?>