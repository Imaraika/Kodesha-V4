## Kodesha-App
## By Ange Ingabire
## Description 
   this is an Android Application which is included in MarketPlace Category,which will help a user to find a house to lent and  contact the houses owner
## Table of contents
* [General info](#generalinfo)
* [Technologies](#technologies)
* [Setup](#setup)
* [BDD](#dbb)
* [Input](#input)
* [Output](#output)
* [Code Examples](#codeexamples)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)
* [Licence](#licence)


## General info
   This project is called Kodesha-APP, is an Android application which will allow the user of it to search a house to rent      according to the location he/she prefer to stay. 
	
## Technologies
  Project is created with:
     * Java 
     * Android
	
## Setup
   To run this project,you may have android studio in you machine :
      - android studio 
      - install emulator for you to run application or you can use USB to run it to your phone 
      - then to get all source code locally these are some git command you may use
   $ cd ../git init 
   $ git clone https://github.com/Imaraika/Kodesha-App.git
   
## BDD
### Behavior
- Please Enter your Location 
- then click on search button bellow which will direct you to second activity of a long list of houses
- click on one house item you like to rent then it will direct you at anothe activity where you will get
  all details about that house
  
### Input
- the location 

### Output
- list of beautiful houses
- deatails about chosen house

## Code Examples
    int[] Images = {R.drawable.backgr1, R.drawable.backgr2, R.drawable.kumaziphoto, R.drawable.mostbeautfl, R.drawable.onather1};
        String[] hsesRoad = new String[]{"707 Kicukiro Ave", "2206 gisz ruhango GD",
                "2816 Beletoire Ave", "8227 Folcroft kigali ", "9227 lene KK", "2227 nyarugenge Ave",
                "8227 sake avenue", "8227 Folcroft Lane", "8227 nyabugoogo avenue", "8227 kigali avenue",
                "1227 sahara avenue", "8227 nyugwe avenue", "8227 ruyenzi avenue",
                "2422 kamonyi avenue", "8220 nyamata avenue"};

        dispLocationText = (TextView) findViewById(R.id.display_Location_TextView);
        listOfHouses = (ListView) findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,hsesRoad);
        listOfHouses.setAdapter(arrayAdapter);
        listOfHouses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String houses = ((TextView) view).getText().toString();
                Toast.makeText(Houses.this, houses, Toast.LENGTH_SHORT).show();
            }

        });
        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        dispLocationText.setText("Houses available at "+ location);
    }
 
## Status
this Project is done except in Play store but you can use it if you want in your phone by running it into your phone as  it mentioned above.

## Inspiration
My All Credits goes to Moringa School for the contents which is well explained.

## Contact
created By inange2013@gmail.com feel free to contact me!

## Licence
Copyright 2019 Ange Ingabire
you may not use this file except in compliance with the License.


