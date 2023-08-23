package org.sjk;

public class Main {

    public static void main(String[] args) {
        DataAccess dataAccess = new DataAccess();
        CandidatesApi candidatesApi = new CandidatesApi(dataAccess);
        CandidatesController candidatesController = new CandidatesController(candidatesApi);

        candidatesController.put("Pluto");
        candidatesController.put("Johnny Bravo");
        System.out.println("Candidates: " + candidatesController.get());

        VotersApi votersApi = new VotersApi(dataAccess);
        VotersController votersController = new VotersController(votersApi);

        votersController.put("Peppa");
        votersController.put("Rumcajs");
        System.out.println("Voters: " + votersController.get());

        VotingApi votingApi = new VotingApi(dataAccess);
        VotingController votingController = new VotingController(votingApi);
        votingController.vote("Peppa", "Pluto");
        votingController.vote("Peppa", "Pluto");
        votingController.vote("Peppa", "Pluto");

        System.out.println("Voters: " + votersController.get());
        System.out.println("Candidates: " + candidatesController.get());
        System.out.println("Votes: " + dataAccess.getVotes());


    }
}
