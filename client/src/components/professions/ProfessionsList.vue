<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by name"
               v-model="name"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
                  @click="searchProfession"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Professions List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(profession, index) in professions"
            :key="index"
            @click="setActiveProfession(profession, index)"
        >
          {{ profession.name }}
        </li>
      </ul>
      <div class="nav-item">
        <router-link to="/addProfession" class="nav-link">Add</router-link>
      </div>
    </div>
    <div class="col-md-6">
      <div v-if="currentProfession">
        <h4>Profession</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentProfession.name }}
        </div>
        <div>
          <label><strong>Note:</strong></label> {{ currentProfession.note }}
        </div>

        <router-link :to="'/professions/' + currentProfession.id" class="badge badge-warning">Edit</router-link>
      </div>
      <div v-else>
        <br/>
        <p>Please click on a Profession...</p>
      </div>
    </div>
  </div>
</template>

<script>
import ProfessionService from "@/services/ProfessionService";

export default {
  name: "profession-list",
  data() {
    return {
      professions: [],
      currentProfession: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveProfessions() {
      ProfessionService.getAll()
          .then(response => {
            this.professions = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    setActiveProfession(profession, index) {
      this.currentProfession = profession;
      this.currentIndex = profession ? index : -1;
    },

    searchProfession() {
      ProfessionService.findByName(this.title)
          .then(response => {
            this.professions = response.data;
            this.setActiveProfession(null);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.retrieveProfessions();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>