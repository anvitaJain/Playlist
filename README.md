# Playlist

Inputfile: 
* Wasn't sure what is a good name for this kind of input file in Netlfix world, so calling it as inputFile
* Input file to derive data models and contents are placed in resource folder. 
* Program expects the file name and will scan resource folder to read the contents, preroll, videos and attributes. 

Launch the program: 
* Interface for third party library integration: Interface: RelevantPlaylist, method: getRelevantPlaylist()
* GetRelevantPlaylistImpl has a main method that can be launched by passing three arguments: <content> <country> <json file name>
* Main method from GetRelevantPlaylistImpl should not be shipped to clients as it is mainly for debugging purpose and running the program locally

Assumptions: 
1. There will be only one content entity pertaining to a specific content. Ex: for MI3 there is only one block which has all the associated
prerolls and videos. 
2. There can be 0 or more contents in the json input file
3. There can be 0 or more prerolls in the json input file
other assumptions as mentioned in the question

POJO: 
* All the pojos are in package: com.playlistExtractor.pojos
* Response POJO: Playlist.java
* All other pojos are used to process the input file. 
