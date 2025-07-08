# read the workflow template
WORKFLOW_TEMPLATE=$(cat .github/workflow-template.yml)

# iterate each route in current directory
# shellcheck disable=SC2045
for ROUTE in $(ls microservices); do
    echo "generating workflow for microservices/${ROUTE}"

    # replace template route placeholder with route name
    WORKFLOW=$(echo "${WORKFLOW_TEMPLATE}" | sed "s/{{ROUTE}}/${ROUTE}/g")

    # save workflow to .github/workflows/{ROUTE}
    echo "${WORKFLOW}" > .github/workflows/${ROUTE}.yml
done