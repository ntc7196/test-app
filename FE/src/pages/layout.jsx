import React from "react";
import { Outlet, Link } from "react-router-dom";
import { Navbar, Nav, NavItem, NavLink } from "reactstrap";

class Layout extends React.Component {
  render() {
    return (
      <div>
        <Navbar color="light" light expand="md">
          <Nav className="mr-auto" navbar>
            <NavItem>
              <NavLink href="/">Home</NavLink>
            </NavItem>
            <NavItem>
              <Link to="/employees">Employees</Link>
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