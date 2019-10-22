## Kodesha-App
## By Ange Ingabire
## Description 
   this is an a continued Android Application of Last week where i have added an API to get information from elsewhere
   the project was about to display the List of Houses and some information about those renting houses
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
   $ git clone https://github.com/Imaraika/KodeshaWithAPI-App.git
   
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
- the user should call the owner of the house you want to rent 
- the user shoul open the map to see the location 

## Code Examples

                   //integrate API

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessRenting> call = client.getHouses(location, "houses");
        call.enqueue(new Callback<YelpBusinessRenting>() {
            @Override
            public void onResponse(Call<YelpBusinessRenting> call, Response<YelpBusinessRenting> response) {

                if (response.isSuccessful()) {
                    houses = response.body().getBusinesses();
                    mAdapter = new HouseListAdapter(Houses.this, houses);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(Houses.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showHouses();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessRenting> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);

                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showHouses() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}

## Status
this Project is done except in Play store but you can use it if you want in your phone by running it into your phone as  it mentioned above.

## Inspiration
My All Credits goes to Moringa School for the contents which is well explained.

## Contact
created By inange2013@gmail.com feel free to contact me!

## Licence
Copyright 2019 Ange Ingabire.



