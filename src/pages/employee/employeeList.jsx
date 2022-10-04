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
      uuid: null,
      openUpdate: false
    };
    this.updateList = this.updateList.bind(this);
  }
  componentDidMount() {
    this.updateList();
  }

  updateList() {
    employeeApi.getList()
      .then(
        data => {
          console.log(data);
          this.setState({ "listEmployee": data })
        }
      )
      .catch(
        err => {
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
        <EmployeeTable
          data={this.state.listEmployee}
          update={(data) => { this.setState({ uuid: data }) }}
          refeshList={() => this.updateList()}
        ></EmployeeTable>
        {
          this.state.uuid &&
          <UpdateModel
            uuid={this.state.uuid}
            onClose={() => this.setState({ openAddNew: false, uuid: null })}
            refeshList={() => this.updateList()}
          />
        }
        {
          this.state.openAddNew &&
          <UpdateModel
            onClose={() => this.setState({ openAddNew: false, uuid: null })}
            refeshList={() => this.updateList()}
          />
        }
      </div>
    );
  }
}

export default EmployeeList