import React from "react";
import { Table } from 'reactstrap';
import PropTypes from 'prop-types';
import { Button } from 'reactstrap';
import EmployeeApi from "../../../api/employeeApi";

class EmployeeTable extends React.Component {
  constructor(props) {
    super(props);

    this.delete = this.delete.bind(this);
  }
  delete(id) {
    EmployeeApi.delete(id)
      .then(
        res =>
          alert("delete success")
      )
  }
  render() {
    return (
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>UUid</th>
            <th>Name</th>
            <th>Dept</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {
            this.props.data.length ? this.props.data.map((item) => {
              return (
                <tr key={item.uuid}>
                  <td>{item.uuid}</td>
                  <td>{item.name}</td>
                  <td>{item.dept}</td>
                  <td>{item.phone}</td>
                  <td>{item.address}</td>
                  <td>
                    <Button color="primary" onClick={() => this.props.update(item)}>Update</Button>
                    <Button color="danger" onClick={() => this.delete(item.uuid)} style={{ "margin-left": "5px" }}>Delete</Button>
                  </td>
                </tr>
              )
            })
              :
              <tr>
                <td colSpan={6}>No-data</td>
              </tr>
          }
        </tbody>
      </Table>
    );
  }
}

EmployeeTable.propTypes = {
  data: PropTypes.array
};

EmployeeTable.defaultProps = {
  data: []
};

export default EmployeeTable;