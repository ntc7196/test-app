import React from "react";
import { Outlet, Link } from "react-router-dom";
import { Navbar, Nav, NavItem, NavLink } from "reactstrap";

class Layout extends React.Component {
  render() {
    return (
      <div className="container">
        <Navbar color="light" light expand="md">
          <Nav className="mr-auto" navbar>
            <NavItem>
              <NavLink href="/">Home</NavLink>
            </NavItem>
            <NavItem>
              <NavLink href="/employees">Employees</NavLink>
            </NavItem>
          </Nav>
        </Navbar>
        <div className="wrapper">
          <Outlet />
        </div>
      </div>
    );
  }
}

export default Layout