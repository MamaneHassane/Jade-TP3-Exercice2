package Main;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import lombok.Data;

@Data
public class Ping extends Agent {
    int numeroBalle;
    public void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                if(numeroBalle<10){
                    ACLMessage ping = new ACLMessage(ACLMessage.INFORM);
                    ping.addReceiver(new AID("Pong", AID.ISLOCALNAME));
                    if(numeroBalle==0) ping.setContent("Balle");
                    else ping.setContent("Balle-"+numeroBalle);
                    send(ping);
                }
                ACLMessage reponse = receive();
                while (reponse == null) {
                    reponse = receive();
                    if(reponse!=null){
                        System.out.println("Pong said to me : "+reponse.getContent());
                        if(reponse.getContent().equals("Balle-10")) myAgent.doDelete();
                        numeroBalle++;
                    }
                }
            }
        });
    }
}
