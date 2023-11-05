create or replace view v_coord_proprietes as (
    select *, st_x(latlng::geometry) lat, st_y(latlng::geometry) lng
    from coord_proprietes
);
