<?php
//REST Server
$server = 'http://www.yunait.com/rest/';
//Method
$method = 'deals';
//Request type (only in case of deals)
$type = 'in';
//Parameters to be passed on to the method
$params = Array(

	'format' => 'json',
	'lat' => 40.407742,
	'lng' => -3.703211,
	'national' => 0,
	'key' => '484f3ccc716c2d25069b416ce0cabedd6fc54572'
);
//We build the URL
$url = $server . $method . '/' . $type . '?' . http_build_query($params);
//We make the request to Yunait
$data = file_get_contents($url);
//We convert the returned JSON to an array
if ( $data ) { $result = json_decode($data, true); }
else { exit(); }
//We process the results
echo 'Found '. $result['total'] . ' deal(s)' . "\n";
foreach ($result['data'] as $deal) {
echo $deal['title'] ."\n";
echo $deal['url'] ."\n\n";
}
echo 'End';

