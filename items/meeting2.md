# [Team Name]
This following is a very simple team meeting template. You should expand it based on the scope and nature of your discussion.

## Team Meeting [2] - Week [9] - [30/04/2023] (11:00AM-12:52PM)
**Absent:** Michael Ostapenko
<br>
**Lead/scribe:** Ryan Yoon

## Agreed Procedure
Stand up Procedure: Sunday 11AM & Tuesday 2PM

## Agenda Items
| Number   |              Item |
|:---------|------------------:|
| [1]      |           [Trees] |
| [2]      | [XML and Storage] |
| [3]      |     [DTO classes] |

## Meeting Minutes
- Implementation of tree in program design. Having an XML file representing massive java file rather than having an ugly file.

- Must interact with firebase since everything that is interacted with will be stored in that.
- User generated quizzes, need some way for teacher to make the quizzes. Reduce complexity, have user make quiz on page and that is XML file that gets stored.
- XML serializers: Serializes in an XML file, data stored in static database, and dynamic data being stored in a real time database.

- Need different data types for different permissions. We don't have a use case at the moment so creating all the DTO classes is very premature.
- Initially creating data types then adding on top of that is better than not having them at all.

- Model View ViewModel architecture.

- Add binding to every relevant XML component. Make bindable functions that update the viewmodel.

## TODO Items
| Task                                                              |            Assignee |
|:------------------------------------------------------------------|--------------------:|
| UI Design for activity fragments. Look into persisting user data. |          Jerry Zhao |
| Working on Signup implementation. Look into firebase integration. |   Karthik Vemireddy |
| Research how to use trees within assignment and implement Parser. |    Matthew Richards |
| Research how to use trees within assignment and implement Parser. |           Ryan Yoon |

## Scribe Rotation  
The following dictates who will be scribe in this and the next meeting.
| Ryan Yoon |
| :---: |
| Matthew Richards |
| Jerry Zhao |
| Karthik Vemireddy |
| Michael Ostapenko |