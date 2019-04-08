<?php 
	require "connect.php";

	$query = "SELECT * FROM playlist";
	$data = mysqli_query($conn, $query);

	class DanhSachPlayList{
		function DanhSachPlayList($idPlayList, $ten, $hinhNen, $hinhIcon){
			$this->IdPlayList = $idPlayList;
			$this->Ten = $ten;
			$this->HinhPlayList = $hinhNen;
			$this->Icon = $hinhIcon;
		}
	}
	$arrayPlayList = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($arrayPlayList, new DanhSachPlayList($row['IdPlayList']
														,$row['Ten']
														,$row['HinhNen']
														,$row['HinhIcon']));
	}
	echo json_encode($arrayPlayList);
 ?>