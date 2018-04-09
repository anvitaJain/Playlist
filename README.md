# Playlist

Things to note for input file: 
1. Input file is located under resources
2. Input file is json file with name like modelData.json

Steps to run: 
1. To run this library locally, you can run the GetRelevantPlaylistImpl class by supplying following arguments: 
  content country jsonFileName

where as, while shipping this library to clients, remove main method. Clients should be calling getRelevantPlaylist().
Interface details can be found at GetRelevantPlaylist interface. 

Assumptions:
1. There will be only one content field pertaining to a specific content name in json input file 
2. There will be only one preroll field pertaining to a specific preroll name in json input file
3. There can be 0 or more contents in the json input file
4. There can be 0 or more prerolls in the json input file



