package Main;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Pong extends Agent {
    int numeroBalle=0;
    public void setup(){
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage reponse = receive(mt);
                while (reponse == null) {
                    reponse = receive(mt);
                    if(reponse!=null) {
                        System.out.println("Ping said to me : "+reponse.getContent());
                        if(numeroBalle<10) numeroBalle++;
                        ACLMessage pong = new ACLMessage(ACLMessage.INFORM);
                        pong.addReceiver(new AID(reponse.getSender().getName()));
                        pong.setContent("Balle-"+numeroBalle);
                        send(pong);
                        if(numeroBalle==10) myAgent.doDelete();
                    }
                }
            }
        });
    }
}
