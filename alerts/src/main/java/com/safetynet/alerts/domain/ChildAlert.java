package com.safetynet.alerts.domain;

import lombok.Data;

import java.util.List;
/*http://localhost:8080/childAlert?address=<address>
This URL must return a list of children (any individual aged 18 years or younger) living
at this address. The list must include the first name, last name of each child,
age, and a list of other household members. If no children are found, this URL may
return an empty string.
*/

@Data
public class ChildAlert {
    private String firstName;
    private String lastName;
    private int age;
    private List<String> householdMembers;

}
