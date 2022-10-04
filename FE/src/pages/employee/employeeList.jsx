import React from "react";
import employeeApi from "../../api/employeeApi";
import UpdateModel from "./components/updateOrCreateModel";
import EmployeeTable from "./components/table";
import { Button } from 'reactstrap';

class EmployeeList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      listEmployee: [],
      updateData: null,
      openUpdate: false
    };
  }
  componentDidMount() {
    employeeApi.getList()
      .then(
        data => {
          console.log(data);
          this.setState({ "listEmployee": data })
        }
      )
      .catch(
        er => {
          console.log(er);

          this.setState({ "listEmployee": [] })
        }
      )
  }

  render() {
    return (
      <div className="employees">
        employee
        <Button color="primary" style={{ "float": "left" }}
          onClick={() => this.setState({ openAddNew: true })}> Add </Button>
        <EmployeeTable data={this.state.listEmployee} update={(data) => { this.setState({ updateData: data }) }}></EmployeeTable>
        {
          this.state.updateData &&
          <UpdateModel
            data={this.state.updateData}
            onClose={() => this.setState({ openAddNew: false, updateData: null })}
          />
        }
        {
          this.state.openAddNew &&
          <UpdateModel
            onClose={() => this.setState({ openAddNew: false, updateData: null })}
          />
        }
      </div>
    );
  }
}

export default EmployeeList