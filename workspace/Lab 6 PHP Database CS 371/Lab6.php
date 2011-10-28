<?php 
$file = fopen("teams2005.txt", 'r');

$array = explode("\n", fread($file, filesize("teams2005.txt")));

$total = sizeof($array);

for ($i = 0; $i < $total; $i++) {
    $array[$i] = explode(",", $array[$i]);
}

$dom = new DomDocument("1.0");

header("Content-Type: text/xsl");

$i = 0;

//while ($r < $total){
//root element
$root = $dom->createElement('teams');
$dom->appendChild($root);

//child element team
$team = $dom->createElement('team');
$root->appendChild($team);

//child element team id
$id = $dom->createElement("teamid");
$team->appendChild($id);

//child text node of team ID
$text = $dom->createTextNode($array[$r][$i]);
$id->appendChild($text);

//child element league
$leag = $dom->createElement("league");
$team->appendChild($leag);

//child element league text
$leagTxt = $dom->createTextNode($array[$r][$i+1]);
$leag->appendChild($leagTxt);

//child element college
$coll = $dom->createElement("college");
$team->appendChild($coll);

//child text node college
$collTxt = $dom->createTextNode($array[$r][$i+2]);
$coll->appendChild($collTxt);

//child element win
$win = $dom->createElement("wins");
$team->appendChild($win);

//child text node win
$winTxt = $dom->createTextNode($array[$r][$i+3]);
$win->appendChild($winTxt);

//child element loss
$loss = $dom->createElement("losses");
$team->appendChild($loss);

//child text node loss
$lossTxt = $dom->createElement($array[$r][$i+3]);
$loss->appendChild($lossTxt);

$r++;
//}

$dom->save("teams.xml");

?>