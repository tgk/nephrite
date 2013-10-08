# nephrite

![Lovely nephrite](https://raw.github.com/tgk/nephrite/master/example/nephrite.jpg)

A Clojure library designed to help design jade templates with as little
hassle as possible. The idea is, you execute the app and point it
towards the base folder of your jade and resource files. It then servers
the translated jade files, feeding them with data from the `_data`
folder in the same base directory.

The example project included with the source of the projects demonstrates how the files must be organised. The file structure is as follows:

```
index.jade          The jade file
nephrite.jpg        A lovely image
_data/index.json    The data used to feed into index.jade
```

Any non-jade files will be served statically.

## Usage

If you have the project cloned, you can run it using lein by, from the
checked out source folder, issuing

    lein run --folder <base folder>

This will start the nephrite process, which will look for jade and
resource files in the base folder, and serve them on port 8000 by
default.

The example project shipped with the source can be served by issuing

    lein run --folder example/

And pointing your browser towards [localhost:8000/index.jade](http://localhost:8000index.jade).

If you do not have lein, or if you're giving the tool to a non-Clojure
developer, you can pack it up as an uberjar

    lein uberjar

and run the jar directly from the command line

    java -jar nephrite-0.1.0-SNAPSHOT-standalone.jar --folder <base folder>

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
