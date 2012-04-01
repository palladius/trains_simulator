Trains Problem
--------------

In some hypothetical country, there are 8 train stations with a single rail track
connecting the train stations. This track forms a big circle.

There are 4 cargo trains, each travelling at a different speed. All trains travel in the
same direction. For safety reasons, a train may not enter the section of the track
between 2 stations if another train is already on that section of the track. In such a
case the train needs to wait at the train station until the other train has reached the
next train station. At the train stations there are sufficient parallel tracks to allow the
trains to overtake one another.

At the train stations, people from a nearby city will (at random times) deliver cargo
that needs to be delivered to another train station. Each piece of cargo will be
addressed to a specific train station.

Trains unload the cargo meant for a train station when they reach that station.
Unloading the cargo takes a small amount of time for each piece of cargo. Once
unloaded, the cargo disappears (it will be taken to a nearby city which is outside the
scope of this simulation).

Once all cargo for that station has been unloaded, a train will load all available cargo
that needs to be transported to another station. This loading of cargo also takes a bit
of time for each piece of cargo. If there are multiple trains at the same station, they
may each load some of the available cargo. Trains have a limited cargo capacity, so
the loading of cargo will stop when the train is full. A train will continue its' journey
once all available cargo has been loaded or when the train is full.

Create a program that simulates the scenario described above using threads. Print all
interesting events (arriving at a station, departing, loading, unloading, etc) to the
screen. Design your program so that the number of stations, the number of trains
and their cargo capacity can be easily modified.

Solution
--------

The solution is a simple CLI client that might be invoked this way (see `Makefile`)

	make clean
	make run

* A station can have multiple trains, a railway is a shared resource with at most ONE train.
* There are 2 threads for visualization and cargo generation, plus Nt where is the number of trains. 

As output, the program provides a log-like standard output and a status file with a synoptic which
helps understand the state of the system. A couple of snapshots have been included in case some problem
would arise with the code. A VerboseThread abstract class has been created to provide a harmonized logging
output, wherever possible.

The `main()` method is `Exercise.java` and it tries to be completely decoupled from the business logic.

The parameters of the problem have been put all in the Country class (which is our singleton and provides a
centralized "world" for application variables. I didn't have time to move those parameters to ARGV or to a
configuration file.

The synchronization among the instances (mostly Trains/Cargos/Stations) have been reached with a few native
mechanisms of Java (I'm using Java 5):

- synchronized methods    (to make sure 2 threads cant access the same method at the same time).
- synchronized statements (ditto, for a subpart of it)
- locks on Java Objects   (to make sure 2 threads cant access the same object at the same time).

I implemented this exercise using Eclipse.

See the single classes JaveDocs for additional info.