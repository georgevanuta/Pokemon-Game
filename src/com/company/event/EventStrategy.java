package com.company.event;

import com.company.pokemon.*;

/*Strategy Design Pattern*/
public interface EventStrategy {
    boolean executeEvent(Antrenor a1, Antrenor a2);
}
