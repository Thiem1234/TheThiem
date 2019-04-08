<?php
	require "connect.php";

	class ChuDe{
		function ChuDe($idChuDe, $tenChuDe, $hinhChuDe){
			$this->IdChuDe = $idChuDe;
			$this->TenChuDe = $tenChuDe;
			$this->HinhChuDe = $hinhChuDe;
		}
	}
	$query = "SELECT * FROM chude";
	$data = mysqli_query($conn, $query);
	$arrayChuDe = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayChuDe, new ChuDe($row['IdChuDe']
										,$row['TenChuDe']
										,$row['HinhChuDe']));
	}
	echo json_encode($arrayChuDe);
  ?>