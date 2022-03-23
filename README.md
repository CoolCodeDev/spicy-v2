# Spicy framework
## About
This is mainly an attempt to implement feature flag functionality to optimise trunk-based development.

## Prerequisities:
- Not tied to any large frameworks, such as Spring or Quarkus.
  - You should be able to use these libraries no matter your framework environment.
- No external libraries
  - You never know what vulnerabilities you might introduce when depending on libraries that can do more than you need. Just look at Log4Shell.

## Todo´s
- Deserialize json to a type
  - Mimic gson with jsonelement, jsonobject, jsonarray
  - Check https://www.w3schools.com/js/js_json_datatypes.asp
- Deserialize non-flat json structures
- Serialize non-flat object structures
