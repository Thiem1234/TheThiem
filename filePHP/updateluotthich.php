<?php 
	require "connect.php";
	$luotThich = "1";
	$idbaihat = "1";
	$query = "SELECT LuotThich FROM baihat WHERE IdBaiHat = '$idbaihat' ";
	$dataLuotThich = mysqli_query($conn,$query);
	$row = mysqli_fetch_assoc($dataLuotThich);
	$tongLuotThich = $row['LuotThich'];

	if (isset($luotThich)) {
		$tongLuotThich = $tongLuotThich + $luotThich;
		$querySum = "UPDATE baihat SET LuotThich = '$tongLuotThich' WHERE IdBaiHat = '$idbaihat'";
		$dataUpdate = mysqli_query($conn,$querySum);
		if ($dataUpdate) {
			echo "Success";
		}else{
			echo "Fail";
		}
	}
 ?>