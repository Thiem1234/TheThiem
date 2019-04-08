<?php 
	require "connect.php";
	$query = "SELECT quangcao.Id, quangcao.HinhAnh, quangcao.NoiDung, quangcao.IdBaiHat, baihat.TenBaiHat, baihat.HinhBaiHat 
	FROM `baihat` INNER JOIN quangcao ON quangcao.IdBaiHat = baihat.IdBaiHat 
	WHERE quangcao.IdBaiHat = baihat.IdBaiHat ";
	$data = mysqli_query($conn, $query);

	class QuangCao{
		function QuangCao($idQuangCao, $hinhAnh, $noiDung, $idBaiHat, $tenBaiHat, $hinhBaiHat){
			$this->IdQuangCao = $idQuangCao;
			$this->HinhAnh = $hinhAnh;
			$this->NoiDung = $noiDung;
			$this->IdBaiHat = $idBaiHat;
			$this->TenBaiHat = $tenBaiHat;
			$this->HinhBaiHat = $hinhBaiHat;

		}
	}
	$mangQuangCao = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mangQuangCao, new QuangCao($row['Id'],
												 $row['HinhAnh'],
												 $row['NoiDung'],
												 $row['IdBaiHat'],
												 $row['TenBaiHat'],
												 $row['HinhBaiHat']));
	}
	echo json_encode($mangQuangCao);
 ?>